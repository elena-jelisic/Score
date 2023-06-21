import {Component, OnInit, ViewChild} from '@angular/core';
import {BieForOasDoc, BieForOasDocListRequest, OasDoc, simpleOasDoc} from '../domain/openapi-doc';
import {MatSort, SortDirection} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import {BusinessContextService} from '../../../../context-management/business-context/domain/business-context.service';
import {OpenAPIService} from '../domain/openapi.service';
import {AccountListService} from '../../../../account-management/domain/account-list.service';
import {AuthService} from '../../../../authentication/auth.service';
import {Location} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ConfirmDialogService} from '../../../../common/confirm-dialog/confirm-dialog.service';
import {forkJoin} from 'rxjs';
import {hashCode} from 'src/app/common/utility';
import {v4 as uuid} from 'uuid';
import {BusinessContext} from '../../../../context-management/business-context/domain/business-context';
import {WorkingRelease} from '../../../../release-management/domain/release';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';
import {PageRequest} from '../../../../basis/basis';
import {finalize} from 'rxjs/operators';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {OasDocAssignDialogComponent} from '../oas-doc-assign-dialog/oas-doc-assign-dialog.component';

@Component({
  selector: 'score-oas-doc-detail',
  templateUrl: './oas-doc-detail.component.html',
  styleUrls: ['./oas-doc-detail.component.css']
})
export class OasDocDetailComponent implements OnInit {
  title = 'Edit Open API Doc';
  oasDocs: simpleOasDoc[];
  oasDoc: OasDoc;
  workingRelease = WorkingRelease;
  businessContextIdList: number[] = [];
  businessContextList: BusinessContext[] = [];
  hashCode;
  bizCtxSearch: string;
  disabled: boolean;

  displayedColumns: string[] = [
    'select', 'state', 'den', 'owner', 'version', 'verb', 'arrayIndicator', 'suppressRoot', 'messageBody',
    'resourceName', 'operationId', 'tagName', 'lastUpdateTimestamp'
  ];
  dataSource = new MatTableDataSource<BieForOasDoc>();
  selection = new SelectionModel<BieForOasDoc>(false, []);
  businessContextSelection = {};
  request: BieForOasDocListRequest;
  loading = false;
  isUpdating: boolean;

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private bizCtxService: BusinessContextService,
              private openAPIService: OpenAPIService,
              private accountService: AccountListService,
              private auth: AuthService,
              private location: Location,
              private router: Router,
              private route: ActivatedRoute,
              private snackBar: MatSnackBar,
              private confirmDialogService: ConfirmDialogService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.oasDoc = new OasDoc();
    this.oasDoc.used = true;
    const oasDocId = this.route.snapshot.params.id;

    // Init BIE list table for OasDoc
    this.request = new BieForOasDocListRequest(this.route.snapshot.queryParamMap,
      new PageRequest('lastUpdateTimestamp', 'desc', 0, 10));
    this.request.access = 'CanView';
    this.paginator.pageIndex = this.request.page.pageIndex;
    this.paginator.pageSize = this.request.page.pageSize;
    this.paginator.length = 0;

    this.sort.active = this.request.page.sortActive;
    this.sort.direction = this.request.page.sortDirection as SortDirection;
    this.sort.sortChange.subscribe(() => {
      this.paginator.pageIndex = 0;
      this.loadBieListForOasDoc();
    });

    forkJoin(
      this.openAPIService.getOasDoc(oasDocId),
      this.openAPIService.getBieForOasDoc(oasDocId)
    )
      .subscribe(([simpleOasDoc, bieForOasDoc]) => {
        this.oasDoc = simpleOasDoc;
        this.hashCode = hashCode(this.oasDoc);
        this.init(this.oasDoc);
        this.loadBieListForOasDoc(true);
      }, _ => {
        this.isUpdating = false;
      });
  }

  init(oasDoc: OasDoc) {
    this.hashCode = hashCode(oasDoc);
    this.oasDoc = oasDoc;
    this.isUpdating = false;
  }

  loadBieListForOasDoc(isInit?: boolean) {
    this.loading = true;

    this.request.page = new PageRequest(
      this.sort.active, this.sort.direction,
      this.paginator.pageIndex, this.paginator.pageSize);

    this.openAPIService.getBieListForOasDoc(this.request).pipe(
      finalize(() => {
        this.loading = false;
      })
    ).subscribe(resp => {
      this.paginator.length = resp.length;
      this.dataSource.data = resp.list.map((elm: BieForOasDoc) => {
        elm.lastUpdateTimestamp = new Date(elm.lastUpdateTimestamp);
        return elm;
      });
      this.dataSource.data.forEach((elm: BieForOasDoc) => {
        this.businessContextSelection[elm.topLevelAsbiepId] = elm.businessContexts[0];
      });

      if (!isInit) {
        this.location.replaceState(this.router.url.split('?')[0], this.request.toQuery());
      }
    }, error => {
      this.dataSource.data = [];
      this.businessContextSelection = {};
    });
  }

  isChanged() {
    return this.hashCode !== hashCode(this.oasDoc);
  }

  isDisabled(oasDoc1: OasDoc) {
    return (this.disabled) ||
      (oasDoc1.oasDocId === undefined || !oasDoc1.oasDocId) ||
      (oasDoc1.title === undefined || oasDoc1.title === '') ||
      (oasDoc1.openAPIVersion === undefined || oasDoc1.openAPIVersion === '') ||
      (oasDoc1.version === undefined || oasDoc1.version === '') ||
      (oasDoc1.licenseName === undefined || oasDoc1.licenseName === '');
  }

  back() {
    this.location.back();
  }

  update() {
    this.checkUniqueness(this.oasDoc, (_) => {
      this.doUpdate();
    });
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  clearFilter() {
    this.bizCtxSearch = '';
    this.applyFilter(this.bizCtxSearch);
  }

  get access(): string {
    if (this.oasDoc) {
      return this.oasDoc.access;
    }
    return '';
  }

  checkUniqueness(oasDoc: OasDoc, callbackFn?) {
    this.openAPIService.checkUniqueness(oasDoc).subscribe(resp => {
      if (!resp) {
        this.openDialogOasDocUpdate();
        return;
      }
      return callbackFn && callbackFn();
    });
  }

  checkOasDocTitle(oasDoc: OasDoc, callbackFn?) {
    this.openAPIService.checkTitleUniqueness(oasDoc).subscribe(resp => {
      if (!resp) {
        this.openDialogOasDocUpdateIgnore();
        return;
      }
      return callbackFn && callbackFn();
    });
  }

  doUpdate() {
    this.openAPIService.updateOasDoc(this.oasDoc).subscribe(_ => {
      this.hashCode = hashCode(this.oasDoc);
      this.snackBar.open('Updated', '', {
        duration: 3000,
      });
      this.router.navigateByUrl('/profile_bie/express/oas_doc');
    });
  }

  openDialogOasDocUpdate() {
    const dialogConfig = this.confirmDialogService.newConfig();
    dialogConfig.data.header = 'Invalid parameters';
    dialogConfig.data.content = [
      'Another Open API Doc with the same title, OpenAPI Version, Doc Version and License Name already exists!'
    ];

    this.confirmDialogService.open(dialogConfig).afterClosed().subscribe(_ => {
    });
  }

  openDialogOasDocUpdateIgnore() {
    const dialogConfig = this.confirmDialogService.newConfig();
    dialogConfig.data.header = 'The OpenAPI Doc already has a variable with the same properties';
    dialogConfig.data.content = [
      'Are you sure you want to update the OpenAPI Doc?'
    ];
    dialogConfig.data.action = 'Update anyway';

    this.confirmDialogService.open(dialogConfig).afterClosed()
      .subscribe(result => {
        if (result) {
          this.doUpdate();
        }
      });
  }

  discard() {
    const dialogConfig = this.confirmDialogService.newConfig();
    dialogConfig.data.header = 'Discard OpenAPI Doc?';
    dialogConfig.data.content = [
      'Are you sure you want to discard this OpenAPI Doc?',
      'The OpenAPI Doc will be permanently removed.'
    ];
    dialogConfig.data.action = 'Discard';

    this.confirmDialogService.open(dialogConfig).afterClosed()
      .subscribe(result => {
        if (result) {
          this.openAPIService.delete(this.oasDoc.oasDocId).subscribe(_ => {
            this.snackBar.open('Discarded', '', {
              duration: 3000,
            });
            this.router.navigateByUrl('/profile_bie/express/oas_doc');
          }, err => {
            console.log(err);
            this.snackBar.open('Discard\'s forbidden! The OpenAPI Doc is used.', '', {
              duration: 5000,
            });
          });
        }
      });
  }

  listBIE() {
    this.router.navigateByUrl('/profile_bie/express/oas_doc/' + this.oasDoc.oasDocId + '/bie_list');
  }

  openDialog(bieForOasDoc?: BieForOasDoc) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.data = {};
    dialogConfig.data.bieForOasDoc = new BieForOasDoc();
    // Default indicator values
    dialogConfig.data.isEditable = this.isEditable();

    const isAddAction: boolean = (bieForOasDoc === undefined);

    this.isUpdating = true;
    const dialogRef = this.dialog.open(OasDocAssignDialogComponent, dialogConfig);
    dialogRef.afterClosed().pipe(finalize(() => {
      this.isUpdating = false;
    })).subscribe(result => {
      if (!result) {
        return;
      }

      const data = this.dataSource.data;
      if (isAddAction) {
        for (const bieAdded of data) {
          if (bieAdded.propertyTerm === result.propertyTerm) {
            this.snackBar.open(result.propertyTerm + ' already exist', '', {
              duration: 3000,
            });

            return;
          }
        }

        result.guid = uuid();
        data.push(result);

        this._updateDataSource(data);
      } else {
        for (const bieAdded of data) {
          if (bieAdded.guid !== result.guid && bieAdded.propertyTerm === result.propertyTerm) {
            this.snackBar.open(result.value + ' already exist', '', {
              duration: 3000,
            });
            return;
          }
        }

        this._updateDataSource(data.map(row => {
          if (row.guid === result.guid) {
            return result;
          } else {
            return row;
          }
        }));
      }
    });
  }

  _updateDataSource(data: BieForOasDoc[]) {
    this.dataSource.data = data;
    this.oasDoc.bieList = data;
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.filter(row => this.isAvailable(row)).length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.select(row));
  }

  select(row: BieForOasDoc) {
    if (this.isAvailable(row)) {
      this.selection.select(row);
    }
  }

  isAvailable(bieForOasDoc: BieForOasDoc) {
    return this.oasDoc.bieList != null;
  }

  toggle(row: BieForOasDoc) {
    if (this.isSelected(row)) {
      this.selection.deselect(row);
    } else {
      this.select(row);
    }
  }

  isSelected(row: BieForOasDoc) {
    return this.selection.isSelected(row);
  }

  isEditable(): boolean {
    return this.access === 'CanEdit';
  }

  isDirty(): boolean {
    return !!this.oasDoc.oasDocId
      || this.oasDoc.title && this.oasDoc.title.length > 0
      || this.oasDoc.description && this.oasDoc.description.length > 0;
  }

  isWorkingRelease(): boolean {
    if (this.oasDoc) {
      return this.oasDoc.releaseId === this.workingRelease.releaseId;
    }
    return false;
  }
  removeBieForOasDoc() {
    const dialogConfig = this.confirmDialogService.newConfig();
    dialogConfig.data.header = 'Remove selected BIE from the OpenAPI Doc?';
    dialogConfig.data.content = ['Are you sure you want to remove the selected BIE?'];
    dialogConfig.data.action = 'Remove';

    this.confirmDialogService.open(dialogConfig).afterClosed()
      .subscribe(result => {
        if (result) {
          const newData = [];
          this.dataSource.data.forEach(row => {
            if (!this.selection.isSelected(row)) {
              newData.push(row);
            }
          });
          this.selection.clear();

          this._updateDataSource(newData);
        }
      });
  }
}

import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatSort, SortDirection} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';
import {ActivatedRoute, Router} from '@angular/router';
import {SimpleSource, SimpleSpecification, Specification, SpecificationList, SpecificationListRequest} from '../domain/specification';
import {SpecificationService} from '../domain/specification.service';
import {PageRequest} from '../../basis/basis';
import {initFilter, saveBranch} from '../../common/utility';
import {Location} from '@angular/common';
import {FormControl} from '@angular/forms';
import {ReplaySubject} from 'rxjs';
import {finalize} from 'rxjs/operators';
import {MatDatepicker, MatDatepickerInputEvent} from '@angular/material/datepicker';
import {ConfirmDialogService} from '../../common/confirm-dialog/confirm-dialog.service';
import {AuthService} from '../../authentication/auth.service';

@Component({
  selector: 'score-specification-list',
  templateUrl: './specification-list.component.html',
  styleUrls: ['./specification-list.component.css']
})
export class SpecificationListComponent implements OnInit {

  title = 'Specification Components List';

  displayedColumns: string[] = [
    'select', 'status', 'componentType', 'componentName'
  ];
  dataSource = new MatTableDataSource<SpecificationList>();
  selection = new SelectionModel<SpecificationList>(true, []);
  loading = false;

  specificationList: SimpleSpecification[] = [];
  sourceList: SimpleSource[] = [];
  specificationListFilterCtrl: FormControl = new FormControl();
  sourceListFilterCtrl: FormControl = new FormControl();
  filteredSourceList: ReplaySubject<SimpleSource[]> = new ReplaySubject<SimpleSource[]>(1);
  filteredSpecificationList: ReplaySubject<SimpleSpecification[]> = new ReplaySubject<SimpleSpecification[]>(1);
  request: SpecificationListRequest;

  contextMenuItem: SpecificationList;
  @ViewChild('dateStart', {static: true}) dateStart: MatDatepicker<any>;
  @ViewChild('dateEnd', {static: true}) dateEnd: MatDatepicker<any>;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private service: SpecificationService,
              private auth: AuthService,
              private snackBar: MatSnackBar,
              private confirmDialogService: ConfirmDialogService,
              private location: Location,
              private router: Router,
              private route: ActivatedRoute,
              private dialog: MatDialog) {
  }

  ngOnInit() {
    this.request = new SpecificationListRequest(this.route.snapshot.queryParamMap,
      new PageRequest('componentName', 'desc', 0, 10));

    this.paginator.pageIndex = this.request.page.pageIndex;
    this.paginator.pageSize = this.request.page.pageSize;
    this.paginator.length = 0;

    this.sort.active = this.request.page.sortActive;
    this.sort.direction = this.request.page.sortDirection as SortDirection;
    this.sort.sortChange.subscribe(() => {
      this.paginator.pageIndex = 0;

    });

    this.service.getSources().subscribe(source => {
      this.sourceList.push(...source);
      initFilter(this.sourceListFilterCtrl, this.filteredSourceList, this.sourceList);
    });

    this.service.getSpecifications().subscribe(specification => {
      this.specificationList.push(...specification);
      initFilter(this.specificationListFilterCtrl, this.filteredSpecificationList, this.specificationList);
    });
  }

  loadSpecificationList(isInit?: boolean) {
    this.loading = true;

    this.request.page = new PageRequest(
      this.sort.active, this.sort.direction,
      this.paginator.pageIndex, this.paginator.pageSize);

    this.service.getSpecificationList(this.request).pipe(
      finalize(() => {
        this.loading = false;
      })
    ).subscribe(resp => {
      this.dataSource.data = resp.list;
      this.paginator.length = resp.length;
      if (!isInit) {
        this.location.replaceState(this.router.url.split('?')[0], this.request.toQuery());
      }
    }, error => {
      this.dataSource.data = [];
    });
  }

  onPageChange(event: PageEvent) {
    this.loadSpecificationList();
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim();
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.select(row));
  }

  select(row: SpecificationList) {
    this.selection.select(row);
  }

  toggle(row: SpecificationList) {
    if (this.isSelected(row)) {
      this.selection.deselect(row);
    } else {
      this.select(row);
    }
  }

  isSelected(row: SpecificationList) {
    return this.selection.isSelected(row);
  }
  onChange(property?: string, source?) {
    if (property === 'specification') {
      saveBranch(this.auth.getUserToken(), this.request.cookieType, source.specificationId);
    }
    if (property === 'filters.den') {
      this.sort.active = '';
      this.sort.direction = '';
    }
  }

  getRouterLink(component: SpecificationList) {
    switch (component.componentType.toUpperCase()) {
      case 'AGGREGATE':
          return '/specification_component/aggregate/' + component.componentId;
      case 'BASIC':
        return '/specification_component/basic/' + component.componentId;

      case 'ASSOCIATION':
        return '/specification_component/association/' + component.componentId;

      case 'DT':
        return '/specification_component/dt/' + component.componentId;

      default:
        return window.location.pathname;
    }
  }
}

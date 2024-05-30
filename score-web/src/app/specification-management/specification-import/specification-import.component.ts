import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Location} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../authentication/auth.service';
import {Specification} from '../domain/specification';
import {SpecificationService} from '../domain/specification.service';

@Component({
  selector: 'score-specification-import',
  templateUrl: './specification-import.component.html',
  styleUrls: ['./specification-import.component.css']
})
export class SpecificationImportComponent implements OnInit {

  title = 'Import Specification';
  disabled: boolean;
  specification: Specification;
  uriForm: FormControl;
  hashCode;

  constructor(private service: SpecificationService,
              private location: Location,
              private route: ActivatedRoute,
              private router: Router,
              private auth: AuthService,
              private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.disabled = false;
    this.specification = new Specification();
    this.uriForm = new FormControl(this.specification.uri, Validators.pattern('\\w+:(\\/?\\/?)[^\\s]+'));
  }

  isDisabled() {
    return !this.uriForm.valid;
  }

  get isDeveloper(): boolean {
    return this.auth.getUserToken().roles.includes('developer');
  }

  ccGapAnalysis() {
    this.specification.uri = this.uriForm.value;
    this.service.ccGapAnalysis(this.specification).subscribe(_ => {
      this.snackBar.open('Analyzed', '', {
        duration: 3000,
      });
      this.router.navigateByUrl('/new_specification');
    });
  }

  import() {
    this.specification.uri = this.uriForm.value;
    this.service.import(this.specification).subscribe(_ => {
      this.snackBar.open('Imported', '', {
        duration: 3000,
      });
      this.router.navigateByUrl('/specification/new');
    });
  }
  approve() {
    this.specification.uri = this.uriForm.value;
    this.service.approve(this.specification).subscribe(_ => {
      this.snackBar.open('Approved', '', {
        duration: 3000,
      });
      this.router.navigateByUrl('/new_specification');
    });
  }

  flatBCC() {
    this.specification.uri = this.uriForm.value;
    this.service.flatBCC(this.specification).subscribe(_ => {
      this.snackBar.open('Flat BCC populated', '', {
        duration: 3000,
      });
      this.router.navigateByUrl('/new_specification');
    });
  }
}

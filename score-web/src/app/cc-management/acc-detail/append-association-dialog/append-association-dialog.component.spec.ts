import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AppendAssociationDialogComponent} from './append-association-dialog.component';

describe('AppendAssociationDialogComponent', () => {
  let component: AppendAssociationDialogComponent;
  let fixture: ComponentFixture<AppendAssociationDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AppendAssociationDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppendAssociationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

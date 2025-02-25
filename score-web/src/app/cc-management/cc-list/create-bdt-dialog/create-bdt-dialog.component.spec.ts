import {ComponentFixture, fakeAsync, TestBed} from '@angular/core/testing';

import {CreateBdtDialogComponent} from './create-bdt-dialog.component';

describe('AppendBccpDialogComponent', () => {
  let component: CreateBdtDialogComponent;
  let fixture: ComponentFixture<CreateBdtDialogComponent>;

  beforeEach(fakeAsync(() => {
    TestBed.configureTestingModule({
      declarations: [CreateBdtDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateBdtDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

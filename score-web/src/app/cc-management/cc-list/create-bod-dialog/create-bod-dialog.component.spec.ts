import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateBodDialogComponent} from './create-bod-dialog.component';

describe('AppendAsccDialogComponent', () => {
  let component: CreateBodDialogComponent;
  let fixture: ComponentFixture<CreateBodDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CreateBodDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateBodDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

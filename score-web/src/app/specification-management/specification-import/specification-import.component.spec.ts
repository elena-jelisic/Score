import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SpecificationImportComponent} from './specification-import.component';

describe('SpecificationImportComponent', () => {
  let component: SpecificationImportComponent;
  let fixture: ComponentFixture<SpecificationImportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SpecificationImportComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpecificationImportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

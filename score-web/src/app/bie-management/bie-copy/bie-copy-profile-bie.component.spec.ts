import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {BieCopyProfileBieComponent} from './bie-copy-profile-bie.component';

describe('BieCopyProfileBieComponent', () => {
  let component: BieCopyProfileBieComponent;
  let fixture: ComponentFixture<BieCopyProfileBieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [BieCopyProfileBieComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BieCopyProfileBieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

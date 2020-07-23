import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicationsViewComponent } from './applications-view.component';

describe('ApplicationsViewComponent', () => {
  let component: ApplicationsViewComponent;
  let fixture: ComponentFixture<ApplicationsViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApplicationsViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplicationsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

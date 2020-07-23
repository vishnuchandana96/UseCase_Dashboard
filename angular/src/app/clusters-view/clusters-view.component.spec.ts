import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClustersViewComponent } from './clusters-view.component';

describe('ClustersViewComponent', () => {
  let component: ClustersViewComponent;
  let fixture: ComponentFixture<ClustersViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClustersViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClustersViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

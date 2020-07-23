import { TestBed } from '@angular/core/testing';

import { TpaService } from './tpa.service';

describe('TpaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TpaService = TestBed.get(TpaService);
    expect(service).toBeTruthy();
  });
});

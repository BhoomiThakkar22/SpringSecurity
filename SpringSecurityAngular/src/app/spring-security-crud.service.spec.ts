import { TestBed } from '@angular/core/testing';

import { SpringSecurityCrudService } from './spring-security-crud.service';
import { HttpClientModule } from '@angular/common/http';
describe('SpringSecurityCrudService', () => {
  beforeEach(() => TestBed.configureTestingModule({imports:[HttpClientModule]}));

  it('should be created', () => {
    const service: SpringSecurityCrudService = TestBed.get(SpringSecurityCrudService);
    expect(service).toBeTruthy();
  });
});

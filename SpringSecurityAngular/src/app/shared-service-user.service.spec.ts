import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { SharedServiceUserService } from './shared-service-user.service';

describe('SharedServiceUserService', () => {
  beforeEach(() => TestBed.configureTestingModule({imports:[HttpClientModule]}));

  it('should be created', () => {
    const service: SharedServiceUserService = TestBed.get(SharedServiceUserService);
    expect(service).toBeTruthy();
  });
});

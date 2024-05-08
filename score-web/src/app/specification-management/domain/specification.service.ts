import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Specification} from './specification';
import {SimpleModule} from '../../module-management/domain/module';
import {PageResponse} from '../../basis/basis';
import {map} from 'rxjs/operators';

@Injectable()
export class SpecificationService {

  constructor(private http: HttpClient) {
  }

  import(specification: Specification): Observable<any> {
    return this.http.put<any>('/api/import_specification', {
      uri: specification.uri,
      description: specification.description
    });
  }

  ccGapAnalysis(specification: Specification): Observable<any> {
    return this.http.put<any>('/api/cc_gap_analysis', {
      uri: specification.uri,
      description: specification.description
    });
  }

  approve(specification: Specification): Observable<any> {
    return this.http.put<any>('/api/approve_cc_gap_analysis', {
      uri: specification.uri,
      description: specification.description
    });
  }
  flatBCC(specification: Specification): Observable<any> {
    return this.http.put<any>('/api/flat_bcc', {
      uri: specification.uri,
      description: specification.description
    });
  }
}

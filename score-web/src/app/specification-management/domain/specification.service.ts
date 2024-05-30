import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {SimpleSource, SimpleSpecification, Specification} from './specification';
import {PageResponse} from '../../basis/basis';
import {map} from 'rxjs/operators';
import {SpecificationList, SpecificationListRequest} from '../../specification-management/domain/specification';
import {CcList, CcListRequest} from '../../cc-management/cc-list/domain/cc-list';
import {OagisComponentType} from '../../cc-management/domain/core-component-node';

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
  getSources(): Observable<SimpleSource[]> {
    return this.http.get<SimpleSource[]>('/api/source_list');
  }
  getSpecifications(): Observable<SimpleSpecification[]> {
    return this.http.get<SimpleSpecification[]>('/api/specifications_list');
  }

  getSpecificationList(request: SpecificationListRequest): Observable<PageResponse<SpecificationList>> {
    if (!request) {
      request = new SpecificationListRequest();
      request.page.pageIndex = -1;
      request.page.pageSize = -1;
    }

    const params = new HttpParams()
      .set('specificationId', '' + request.specification.specificationId)
      .set('sourceId', '' + + request.source.sourceId)
      .set('sortActive', request.page.sortActive)
      .set('sortDirection', request.page.sortDirection)
      .set('pageIndex', '' + request.page.pageIndex)
      .set('pageSize', '' + request.page.pageSize);
    return this.http.get<PageResponse<SpecificationList>>('/api/specification_list', {params});
  }
}

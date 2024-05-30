import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {SimpleSource, SimpleSpecification, Specification} from './specification';
import {PageResponse} from '../../basis/basis';
import {SpecificationList, SpecificationListRequest} from '../../specification-management/domain/specification';


@Injectable()
export class SpecificationService {
  constructor(private http: HttpClient) {
  }

  import(documentName: string, rootFolderPath: string, specificationType: string, sourceName: string): Observable<any> {
    const params = new HttpParams()
      .set('documentName', '' + + 'QIFResults.xsd')
      .set('rootFolderPath', '' + '/Users/enj2/Documents/QIF3.0-2018-ANSI/xsd/QIFApplications')
      .set('specificationType', '' + + 'Standard library')
      .set('sourceName', '' + + 'QIF');
    return this.http.put<any>('/api/import_specification', {
      documentName,
      rootFolderPath,
      specificationType,
      sourceName});
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

import {PageRequest} from '../../basis/basis';
import {ParamMap} from '@angular/router';
import {base64Decode, base64Encode} from '../../common/utility';
import {HttpParams} from '@angular/common/http';

export class SimpleSpecification {
  specificationId: number;
  uri: string;
  specificationName: string;
}

export class SimpleSource{
  sourceId: number;
  sourceName: string;
}

export class Specification {
  specificationId: number;
  uri: string;
  specificationName: string;
  description: string;
}

export class Source {
  sourceId: number;
  sourceName: string;
}

export class SpecificationList {
  componentId: number;
  status: string;
  componentName: string;
  componentType: string;
  description: string;
}

export class SpecificationListRequest {
  specification: SimpleSpecification;
  source: SimpleSource;
  cookieType: string;
  filters: {
    sourceID: string;
    specificationID: string;
  };
  sourceIDs: string[] = [];
  specificationIDs: string[] = [];
  page: PageRequest = new PageRequest();

  constructor(paramMap?: ParamMap, defaultPageRequest?: PageRequest) {
    const q = (paramMap) ? paramMap.get('q') : undefined;
    const params = (q) ? new HttpParams({fromString: base64Decode(q)}) : new HttpParams();
    this.page.sortActive = params.get('sortActive');
    if (!this.page.sortActive) {
      this.page.sortActive = (defaultPageRequest) ? defaultPageRequest.sortActive : '';
    }
    this.page.sortDirection = params.get('sortDirection');
    if (!this.page.sortDirection) {
      this.page.sortDirection = (defaultPageRequest) ? defaultPageRequest.sortDirection : '';
    }
    if (params.get('pageIndex')) {
      this.page.pageIndex = Number(params.get('pageIndex'));
    } else {
      this.page.pageIndex = (defaultPageRequest) ? defaultPageRequest.pageIndex : 0;
    }
    if (params.get('pageSize')) {
      this.page.pageSize = Number(params.get('pageSize'));
    } else {
      this.page.pageSize = (defaultPageRequest) ? defaultPageRequest.pageSize : 0;
    }

    this.sourceIDs = (params.get('sourceIds')) ? Array.from(params.get('sourceIds').split(',')) : [];
    this.specificationIDs = (params.get('specificationIds')) ? Array.from(params.get('specificationIds').split(',')) : [];
    this.filters = {
      sourceID: params.get('sourceIds') || '',
      specificationID: params.get('specificationIds') || ''
    };
  }

  toQuery(): string {
    let params = new HttpParams()
      .set('sortActive', this.page.sortActive)
      .set('sortDirection', this.page.sortDirection)
      .set('pageIndex', '' + this.page.pageIndex)
      .set('pageSize', '' + this.page.pageSize);

    if (this.specificationIDs && this.specificationIDs.length > 0) {
      params = params.set('specificationIds', this.specificationIDs.join(','));
    }
    if (this.sourceIDs && this.sourceIDs.length > 0) {
      params = params.set('sourceIds', this.sourceIDs.join(','));
    }
    const str = base64Encode(params.toString());
    return (str) ? 'q=' + str : undefined;
  }
}


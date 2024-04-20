import {PageRequest} from '../../basis/basis';
import {ParamMap} from '@angular/router';
import {base64Decode, base64Encode} from '../../common/utility';
import {HttpParams} from '@angular/common/http';

export class Specification {
  specificationId: number;
  uri: string;
  description: string;
}


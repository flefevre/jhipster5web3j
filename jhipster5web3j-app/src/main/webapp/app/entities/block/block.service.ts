import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBlock } from 'app/shared/model/block.model';

type EntityResponseType = HttpResponse<IBlock>;
type EntityArrayResponseType = HttpResponse<IBlock[]>;

@Injectable({ providedIn: 'root' })
export class BlockService {
    private resourceUrl = SERVER_API_URL + 'api/blocks';

    constructor(private http: HttpClient) {}

    create(block: IBlock): Observable<EntityResponseType> {
        return this.http.post<IBlock>(this.resourceUrl, block, { observe: 'response' });
    }

    update(block: IBlock): Observable<EntityResponseType> {
        return this.http.put<IBlock>(this.resourceUrl, block, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBlock>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBlock[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Block } from 'app/shared/model/block.model';
import { BlockService } from './block.service';
import { BlockComponent } from './block.component';
import { BlockDetailComponent } from './block-detail.component';
import { BlockUpdateComponent } from './block-update.component';
import { BlockDeletePopupComponent } from './block-delete-dialog.component';
import { IBlock } from 'app/shared/model/block.model';

@Injectable({ providedIn: 'root' })
export class BlockResolve implements Resolve<IBlock> {
    constructor(private service: BlockService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((block: HttpResponse<Block>) => block.body);
        }
        return Observable.of(new Block());
    }
}

export const blockRoute: Routes = [
    {
        path: 'block',
        component: BlockComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipster5Web3JApp.block.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'block/:id/view',
        component: BlockDetailComponent,
        resolve: {
            block: BlockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipster5Web3JApp.block.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'block/new',
        component: BlockUpdateComponent,
        resolve: {
            block: BlockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipster5Web3JApp.block.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'block/:id/edit',
        component: BlockUpdateComponent,
        resolve: {
            block: BlockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipster5Web3JApp.block.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const blockPopupRoute: Routes = [
    {
        path: 'block/:id/delete',
        component: BlockDeletePopupComponent,
        resolve: {
            block: BlockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipster5Web3JApp.block.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jhipster5Web3JSharedModule } from 'app/shared';
import {
    BlockComponent,
    BlockDetailComponent,
    BlockUpdateComponent,
    BlockDeletePopupComponent,
    BlockDeleteDialogComponent,
    blockRoute,
    blockPopupRoute
} from './';

const ENTITY_STATES = [...blockRoute, ...blockPopupRoute];

@NgModule({
    imports: [Jhipster5Web3JSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [BlockComponent, BlockDetailComponent, BlockUpdateComponent, BlockDeleteDialogComponent, BlockDeletePopupComponent],
    entryComponents: [BlockComponent, BlockUpdateComponent, BlockDeleteDialogComponent, BlockDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jhipster5Web3JBlockModule {}

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Jhipster5Web3JTransactionModule } from './transaction/transaction.module';
import { Jhipster5Web3JBlockModule } from './block/block.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        Jhipster5Web3JTransactionModule,
        Jhipster5Web3JBlockModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jhipster5Web3JEntityModule {}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IBlock } from 'app/shared/model/block.model';
import { BlockService } from './block.service';

@Component({
    selector: 'jhi-block-update',
    templateUrl: './block-update.component.html'
})
export class BlockUpdateComponent implements OnInit {
    private _block: IBlock;
    isSaving: boolean;

    constructor(private blockService: BlockService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ block }) => {
            this.block = block;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.block.id !== undefined) {
            this.subscribeToSaveResponse(this.blockService.update(this.block));
        } else {
            this.subscribeToSaveResponse(this.blockService.create(this.block));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBlock>>) {
        result.subscribe((res: HttpResponse<IBlock>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get block() {
        return this._block;
    }

    set block(block: IBlock) {
        this._block = block;
    }
}

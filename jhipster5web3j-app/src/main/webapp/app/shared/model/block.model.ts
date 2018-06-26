import { ITransaction } from 'app/shared/model//transaction.model';

export interface IBlock {
    id?: number;
    address?: string;
    transactions?: ITransaction[];
}

export class Block implements IBlock {
    constructor(public id?: number, public address?: string, public transactions?: ITransaction[]) {}
}

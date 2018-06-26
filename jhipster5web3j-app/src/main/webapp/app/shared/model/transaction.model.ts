export interface ITransaction {
    id?: number;
    address?: string;
    blockAddress?: string;
    blockId?: number;
}

export class Transaction implements ITransaction {
    constructor(public id?: number, public address?: string, public blockAddress?: string, public blockId?: number) {}
}

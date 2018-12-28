import {SportObject} from './sport.object';

export class GameLobby {
  readonly _id: number;
  readonly _level: string;
  readonly _date: string;
  readonly _discipline: string;
  readonly _needed: number;
  readonly _cost: number;
  readonly _owner: string;
  readonly _sportObject: SportObject;


  constructor(level: string, date: string, discipline: string, needed: number, cost: number, owner: string, sportObject: SportObject, id?: number) {
    this._id = id;
    this._level = level;
    this._date = date;
    this._discipline = discipline;
    this._needed = needed;
    this._cost = cost;
    this._owner = owner;
    this._sportObject = sportObject;
  }




  get id(): number {
    return this._id;
  }

  get level(): string {
    return this._level;
  }

  get date(): string {
    return this._date;
  }

  get discipline(): string {
    return this._discipline;
  }

  get needed(): number {
    return this._needed;
  }

  get cost(): number {
    return this._cost;
  }

  get owner(): string {
    return this._owner;
  }

  get sportObject(): SportObject {
    return this._sportObject;
  }
}

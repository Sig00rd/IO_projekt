export class SportObject {
  readonly _id: number;
  readonly _address: string;
  readonly _city: string;
  readonly _name: string;
  readonly _type: string;


  constructor(address: string, city: string, name: string, type: string, id?: number) {
    this._id = id;
    this._address = address;
    this._city = city;
    this._name = name;
    this._type = type;
  }

  get id(): number {
    return this._id;
  }

  get address(): string {
    return this._address;
  }

  get city(): string {
    return this._city;
  }

  get name(): string {
    return this._name;
  }

  get type(): string {
    return this._type;
  }
}

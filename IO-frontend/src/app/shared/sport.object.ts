export class SportObject {
  name: string;
  address: string;
  city: string;
  type: string;


  constructor(address: string, city: string, name: string, type: string) {
    this.name = name;
    this.address = address;
    this.city = city;
    this.type = type;
  }

}

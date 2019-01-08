import {SportObject} from './sport.object';

export class GameForm {
  cost: number;
  needed: number;
  date: string;
<<<<<<< HEAD
  priorityDate: string;
  sportObjectName: string;
  disciplineName: string;
  pitchRoles: object;
=======
  sportObjectName: string;
  disciplineName: string;
  priorityDate: string;
  pitchRoles: Object;
>>>>>>> master
  level: string;


  constructor(cost: number, needed: number, date: string, priorityDate: string,
<<<<<<< HEAD
              sportObjectName: string, disciplineName: string, pitchRoles: object, level: string) {
=======
              sportObjectName: string, disciplineName: string, pitchRoles: Object, level: string) {
>>>>>>> master
    this.cost = cost;
    this.needed = needed;
    this.date = date;
    this.priorityDate = priorityDate;
    this.sportObjectName = sportObjectName;
<<<<<<< HEAD
    this.disciplineName = disciplineName;
    this.pitchRoles = pitchRoles;
    this.level = level;
  }
=======
    this.disciplineName = disciplineName.toUpperCase();
    this.pitchRoles = pitchRoles;
    this.level = level;
  }

>>>>>>> master
}

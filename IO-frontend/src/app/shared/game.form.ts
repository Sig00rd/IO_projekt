import {SportObject} from './sport.object';

export class GameForm {
  cost: number;
  needed: number;
  date: string;
  priorityDate: string;
  sportObjectName: string;
  disciplineName: string;
  pitchRoles: Object;
  level: string;


  constructor(cost: number, needed: number, date: string, priorityDate: string, sportObjectName: string, disciplineName: string, pitchRoles: Object, level: string) {
    this.cost = cost;
    this.needed = needed;
    this.date = date;
    this.priorityDate = priorityDate;
    this.sportObjectName = sportObjectName;
    this.disciplineName = disciplineName.toUpperCase();
    this.pitchRoles = pitchRoles;
    this.level = level;
  }
}

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';

export class Activity {
  activity: string;
  date: Date;
  count: number;
}

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  constructor() { }

  public getAllActivities(): Observable<Activity[]> {
    const activities: Activity[] = [
      {
        activity: 'suburi',
        date: new Date('2020-03-16'),
        count: 100
      },
      {
        activity: 'suburi',
        date: new Date('2020-03-17'),
        count: 100
      },
      {
        activity: 'suburi',
        date: new Date('2020-03-18'),
        count: 100
      },
      {
        activity: 'suburi',
        date: new Date('2020-03-19'),
        count: 150
      },
      {
        activity: 'suburi',
        date: new Date('2020-03-22'),
        count: 150
      }];
    console.log(activities);
    return Observable.of(activities);
  }
}

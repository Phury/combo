import {Component, OnInit} from '@angular/core';
import {Activity, ActivityService} from './services/activity.service';
import {Observable} from 'rxjs';
import {map, reduce} from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  suburi$: Observable<Activity[]>;
  suburiTotal$: Observable<number>;
  suburiStreak$: Observable<number>;

  constructor(private activityService: ActivityService) {
  }

  ngOnInit() {
    this.suburi$ = this.activityService.getAllActivities();
    this.suburiTotal$ = this.suburi$.pipe(
        map( activities => activities.reduce((acc, activity) => acc + activity.count, 0))
    );
    const now = new Date().setUTCHours(0, 0, 0, 0);
    this.suburiStreak$ = this.suburi$.pipe(
        map(activities => activities.reduce((acc, activity, i) => {
          if ((new Date().setUTCHours(0, 0, 0, 0) - new Date(activity.date).setUTCHours(0, 0, 0, 0)) === i * 86400000) {
            return acc + 1;
          }
          return acc;
        }, 0))
    );
  }

}

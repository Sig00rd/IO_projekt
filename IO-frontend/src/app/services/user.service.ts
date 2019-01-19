import {User} from '../shared/user';


export class UserService {
  user: User;

  constructor() {
  }

  isLoggedIn () {
    const promise = new Promise(
      resolve => resolve(this.user != null)
    );
    return promise;
  }

}

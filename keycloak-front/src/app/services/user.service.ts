import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';
import { User } from '../interfaces/user';
import { Observable } from 'rxjs';
import { Token } from '../interfaces/token';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<Token> {
    const user: User = { username, password };
    return this.http.post<Token>(
      `${environment.baseUrl}${environment.login}`,
      user
    );
  }
}

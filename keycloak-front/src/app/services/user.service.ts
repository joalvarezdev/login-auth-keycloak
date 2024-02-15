import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { User } from '../interfaces/user';
import { Observable } from 'rxjs';
import { Token } from '../interfaces/token';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private backendUrl: string;

  constructor(private http: HttpClient) {
    this.backendUrl = environment.baseUrl;
  }

  login(username: string, password: string): Observable<Token> {
    const user: User = { username, password };
    return this.http.post<Token>(`${this.backendUrl}/users/login`, user);
  }
}

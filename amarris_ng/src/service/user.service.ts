import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDto } from '../page/users/User.component';

@Injectable({ providedIn: 'root' }) // this annotation allows to use this class "UserService" in the "root"
export class UserService {
  private apiUrl = '/api/users';

  constructor(private http: HttpClient) {}

  getAllUsers() {
    return this.http.get<UserDto[]>(`${this.apiUrl}/all`);
  }

  getUserById(id: string) {
    return this.http.get<UserDto>(`${this.apiUrl}/${id}`);
  }

  createUser(user: UserDto) {
    return this.http.post<UserDto>(`${this.apiUrl}/create`, user);
  }

  searchUsers(term: string) {
    return this.http.get<UserDto[]>(`${this.apiUrl}/search?term=${term}`);
  }

  deleteUser(id: string) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
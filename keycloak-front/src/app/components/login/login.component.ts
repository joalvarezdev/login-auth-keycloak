import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { UserService } from '../../services/user.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  public username = '';
  public password = '';

  constructor(
    private _userService: UserService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {}

  login() {
    this._userService.login(this.username, this.password).subscribe({
      next: (data) => {
        console.log(data);
        console.log(data.access_token);
        this.toastr.success('User login correctly!', 'Success', {
          timeOut: 3000,
        });
      },
      error: (wrong) => {
        this.toastr.error(wrong, 'Error', {
          timeOut: 3000,
        });
      },
    });
  }
}

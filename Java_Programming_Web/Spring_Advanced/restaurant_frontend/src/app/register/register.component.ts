import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { SnackbarService } from '../services/snackbar.service';
import { MatDialogRef } from '@angular/material/dialog';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { GlobalConstants } from '../shared/global-constants';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  password = true;
  confirmPassword = true;
  passwordMismatch = false;

  registerForm: any = FormGroup;
  responseMessage: any;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private snackbarService: SnackbarService,
    public dialogRef: MatDialogRef<RegisterComponent>,
    private ngxService: NgxUiLoaderService
  ) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      name: [
        null,
        [Validators.required, Validators.pattern(GlobalConstants.nameRegex)],
      ],
      email: [
        null,
        [Validators.required, Validators.pattern(GlobalConstants.emailRegex)],
      ],
      contactNumber: [
        null,
        [
          Validators.required,
          Validators.pattern(GlobalConstants.contactNumberRegex),
        ],
      ],
      password: [null, [Validators.required]],
      confirmPassword: [null, [Validators.required]],
    });

     this.registerForm.valueChanges.subscribe(() => {
    this.validateSubmit();
  });
  }

  validateSubmit(): void {
    this.passwordMismatch = this.registerForm.value.password !== this.registerForm.value.confirmPassword;
  }

  handleSubmit() {
    this.ngxService.start();
    var formData = this.registerForm.value;
    var data = {
      name: formData.name,
      email: formData.email,
      contactNumber: formData.contactNumber,
      password: formData.password,
    };

    this.userService.register(data).subscribe(
      (response: any) => {
        this.ngxService.stop();
        this.dialogRef.close();
        this.responseMessage = response?.message;
        this.snackbarService.openSnackBar(this.responseMessage, "");
        this.router.navigateByUrl('/');
      },
      (error) => {
        this.ngxService.stop();
        if(error.error?.message) {
          this.responseMessage = error.error?.message
        }  else {
          this.responseMessage = GlobalConstants.genericError;
        }

        this.snackbarService.openSnackBar(this.responseMessage, GlobalConstants.error);

      }
    );
  }
}

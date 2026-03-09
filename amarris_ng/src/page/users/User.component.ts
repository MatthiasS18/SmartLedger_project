import { CommonModule } from "@angular/common";
import { ChangeDetectorRef, Component, inject } from "@angular/core";
import { UserService } from "../../service/user.service";
import { MatDialog } from "@angular/material/dialog";
import { ReusableDialogComponent } from "../../components/dialog/Dialog.component";
import { FormsModule } from "@angular/forms";

export interface UserDto {
  id: string;
  username: string;
  email: string;
}

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './User.component.html'
}) 

export class User {

    users: UserDto[] = [];
    searchTerm: string = '';

    constructor(private userService : UserService,
        private cdr: ChangeDetectorRef
    ){}

    ngOnInit(){
      this.loadUsers()
    }

    loadUsers(){
          this.userService.getAllUsers().subscribe(data => {            
            this.users = data;
                  this.cdr.detectChanges();
        })
    }



readonly dialog = inject(MatDialog);

openCreateUserDialog(): void {
  const dialogRef = this.dialog.open(ReusableDialogComponent, {
    data: {
      title: 'Create an user',
      fields: [
        { label: 'Username', key: 'username', value: '' },
        { label: 'Email', key: 'email', value: '' },
      ],
      onConfirm: (data: any) => this.userService.createUser(data)
    }
  });

    dialogRef.afterClosed().subscribe(result => {
        if (result) this.loadUsers();
    });
}

get filteredInvoices() {
  return this.users;
}

onSearch(term: string) {
  if (term.trim() === '') {
    this.loadUsers();
  } else {
    this.userService.searchUsers(term).subscribe(data => {
      this.users = data;      
    });
  }
}

}
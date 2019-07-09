export class User {
    public id: number;
    public firstName: string;
    public lastName: string;
    public role: string;
    public emailId: string;
    public password: string;
    constructor(firstName, lastName, roleId, email, password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = roleId;
        this.emailId = email;
        this.password = password;
    }
    

}
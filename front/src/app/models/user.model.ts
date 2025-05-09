export interface User {
  user_id: number;
  email: string;
  password?: string;
  name: string;
  firstname: string;
  birthdate?: Date;
  phone?: string;
  address?: any;
  role: 'CUSTOMER' | 'SUPPORT';
}

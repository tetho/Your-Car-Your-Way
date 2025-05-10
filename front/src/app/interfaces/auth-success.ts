import { User } from "../models/user.model";

export interface AuthSuccess {
    token: string;
    user: User;
}

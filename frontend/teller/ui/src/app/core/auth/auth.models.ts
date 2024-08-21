export interface AuthTokenRequest {
    username: string;
    password: string;
}

export interface User {
    preferred_username: string;
    name: string;
    given_name: string;
    family_name: string;
    scope: string;
    sub: string;
}

export interface ResponseError {
    code: number;
    message: string;
}

export interface SignInSuccess {
    access_token: string;
    expires_in: number;
    refresh_token: string;
    refresh_expires_in: number;
}

export type SignInResponse = SignInSuccess | ResponseError;
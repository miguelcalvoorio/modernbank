import { InjectionToken } from '@angular/core';

export interface PrivateModuleConfig {
    privateUrlPath: string;
}

export const PRIVATE_MODULE_CONFIG = new InjectionToken<PrivateModuleConfig>('Private module config');

export const DEFAULT_PRIVATE_MODULE_CONFIG: PrivateModuleConfig = { privateUrlPath: 'private' };
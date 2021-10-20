import {config, appFetch} from './appFetch';

export const findPacks = (page, onSuccess) => {    
    let path = `/packs?page=${page}`;

    appFetch(path, config('GET'), onSuccess);
}
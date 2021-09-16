import {addLocaleData} from 'react-intl';
import en from 'react-intl/locale-data/en';
import es from 'react-intl/locale-data/es';
import gl from 'react-intl/locale-data/gl';

import messages from './messages';

export const initReactIntl = () => {

    addLocaleData([...en, ...es, ...gl]);

    let locale = (navigator.languages && navigator.languages[0]) ||
        navigator.language || navigator.userLanguage || 'en';
    const localeWithoutRegionCode = locale.toLowerCase().split(/[_-]+/)[0];
    const localeMessages = messages[locale] || 
        messages[localeWithoutRegionCode] || messages['en'];

    locale = localeMessages === messages['en'] ? 'en' : locale;

    return {locale, messages: localeMessages};

}


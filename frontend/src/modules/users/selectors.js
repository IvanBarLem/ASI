const getModuleState = (state) => state.users;

export const getUser = (state) => getModuleState(state).user;

export const getName = (state) =>
    isLoggedIn(state) ? getUser(state).firstName : null;

export const isLoggedIn = (state) => getUser(state) !== null;

export const isGerente = (state) => {
    const user = getUser(state)
    if (user !== null) {
        return user.role === "GERENTE";
    }
}

export const isAgente = (state) => {
    const user = getUser(state)
    if (user !== null) {
        return user.role === "AGENTE";
    }
}

export const isInformatico = (state) => {
    const user = getUser(state)
    if (user !== null) {
        return user.role === "INFORMATICO";
    }
}

export const getEmail = (state) =>
    isLoggedIn(state) ? getUser(state).email : null;

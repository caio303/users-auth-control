export type LoginResponseType = {
    authenticatedUser: {
        id: string,
        nome: string,
        cpf: string,
        email: string,
        descricao: string
    },
    token: string
}   
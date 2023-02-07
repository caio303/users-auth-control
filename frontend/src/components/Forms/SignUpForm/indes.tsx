import { Container } from "../styles";
import * as yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup'
import { SubmitHandler, useForm} from 'react-hook-form'
import { SignUpValidationType } from "../../../types/SignUpValidationType";
import { dataNascRegExp } from "../../../utils/regexps/dataNascRegExp";
import { SignUpFieldsType } from "../../../types/SignUpFieldsType";
import { crudApi } from "../../../services/crudAPi";
import { useState } from "react";

const createUserFormSchema = yup.object().shape({
    nome: yup.string().required("Preencha o nome").max(30),
    sobrenome: yup.string().max(30),
    cpf: yup.string().required("Preencha o CPF").length(11),
    email: yup.string().email("Insira um email válido").required("Preencha o email").max(60),
    dataNasc: yup.string().required("Preencha a data").length(10,"Preencha neste formato: DD/MM/AAAA"),
    senha: yup.string().required("Preencha a senha").min(8,"A senha precisa ter no mínimo 8 caracteres")
})

export const SignUpForm = () => {

    const [userCreated,setUserCreated] = useState<boolean>(false);
    const [dataNascRegexpError,setDataNascRegexpError] = useState<boolean>(false);
    const [userAlredyExistsError,setUserAlredyExistsError] = useState<boolean>(false);

    const { 
        register, 
        handleSubmit, 
        formState: {errors} 
    } = useForm<SignUpValidationType>({
        resolver: yupResolver(createUserFormSchema)
    })

    const handleSignUpUser: SubmitHandler<SignUpValidationType> = async (values) => {
        if(!values.dataNasc.match(dataNascRegExp)) return setDataNascRegexpError(true);
        setDataNascRegexpError(false);

        const nome = values.sobrenome? values.nome + " " + values.sobrenome : values.nome;
        const descricao = values.descricao?? "";

        const newUser: SignUpFieldsType = {
            nome: nome,
            cpf: values.cpf,
            email: values.email,
            descricao: descricao,
            dataNasc: values.dataNasc,
            senha: values.senha,
        }

        try {
            await crudApi.post("usuario/cadastro",
                {...newUser}
            ).then(() => {
                //Displays success message when user is created successfully
                setUserCreated(true);
                setTimeout(() => setUserCreated(false),1500)
            })
        }catch(error) {
            // Displays error message when user alredy exists (by cpf)
            // @ts-ignore
            if(error.response.status === 409) {
                setUserAlredyExistsError(true)
                setTimeout(() => setUserAlredyExistsError(false),1500)
                return console.warn("User already exists");
                // @ts-ignore
            }else {
                console.warn(error)
            }
        }
        
    }

    return (
        <Container>
            <section>
                <div id="form-header">
                    <h1>CADASTRE-SE</h1>
                    <p>Registre-se em nossa plataforma de graça!</p>
                </div>

                <form onSubmit={handleSubmit(handleSignUpUser)}>

                    <div className="input-wrapper">
                        <label>
                            <div className="label">
                                Nome *    
                            </div>
                            <input 
                                type="text"
                                // @ts-ignore
                                name="nome" 
                                {...register('nome')}
                                maxLength={30} 
                                placeholder="Ex.: Silvio"
                                />
                            {errors.nome && <span>{errors.nome.message}</span>}
                        </label>
                        <label>
                            <div className="label">
                                Sobrenome
                            </div>
                            <input 
                                type="text" 
                                // @ts-ignore
                                name="sobrenome"
                                {...register('sobrenome')}
                                maxLength={30} 
                                placeholder="Ex.: Santos"
                                />
                            {errors.sobrenome && <span>{errors.sobrenome.message}</span>}
                        </label>
                    </div>

                    <label>
                        <div className="label">
                            Email *
                        </div>
                        <input 
                            type="email"
                            // @ts-ignore
                            name="email"
                            {...register('email')}
                            maxLength={60} 
                            placeholder="Ex.: silviosantos@sbt.com"
                            />
                        {errors.email && <span>{errors.email.message}</span>}
                    </label>
                
                    <div className="input-wrapper">
                        <label>
                            <div className="label">
                                CPF *
                            </div>
                            <input 
                                type="tel"
                                // @ts-ignore 
                                name="cpf"
                                {...register('cpf')}
                                maxLength={11}
                                placeholder="Ex.: 12345678910"
                                />
                            {errors.cpf && <span>{errors.cpf.message}</span>}
                        </label>
                        <label>
                            <div className="label">
                                Data de nasc. *
                            </div>
                            <input 
                                type="text" 
                                // @ts-ignore
                                name="dataNasc"
                                {...register('dataNasc')}
                                maxLength={10}
                                placeholder="Ex.: 04/05/1995"
                                />
                            {errors.dataNasc && <span>{errors.dataNasc.message}</span>}
                            {dataNascRegexpError && <span>"Insira uma data válida"</span>}
                        </label>
                    </div>

                    <label>
                        <div className="label">
                            Senha *
                        </div>
                        <input 
                            type="password" 
                            // @ts-ignore
                            name="senha"
                            {...register('senha')}
                            placeholder="Ex.: S3nh4f0rte@_3306"
                            />
                        {errors.senha && <span>{errors.senha.message}</span>}
                    </label>

                    <label>
                        <div className="label">
                            Descrição    
                        </div>
                        <textarea 
                            placeholder="Ex.: Sou desenvolvedor web!"
                            {...register('descricao')}
                            />
                    </label>

                    <button type="submit">{userAlredyExistsError ? 'Usuário já cadastrado' : (userCreated? 'Usuário Criado!' : 'Enviar')}</button>

                </form>
            </section>
        </Container>
    )
} 
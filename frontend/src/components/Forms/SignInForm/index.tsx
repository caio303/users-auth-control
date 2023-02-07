import { Link } from "react-router-dom";
import { Container } from "../styles";
import * as yup from 'yup';
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";

const createSignInFormSchema = yup.object().shape({
    cpf: yup.string().required("Insira o CPF").length(11,"Insira um CPF válido"),
    senha: yup.string().required("Insira a senha").min(8,"A senha precisa ter no mínimo 8 caracteres")
})

export const SignInForm = () => {
    
    const {
        register,
        handleSubmit,
        formState: {errors}
    } = useForm({
            resolver: yupResolver(createSignInFormSchema)
    })

    return (
        <Container>
            <section>
                <div id="form-header">
                    <h1>ENTRE</h1>
                    <p><span>Ou </span><Link to="/cadastro">crie uma conta</Link></p>
                </div>
    
                <form onSubmit={handleSubmit(()=>alert("Validou"))}>
    
                    <label>
                        <div className="label">
                            CPF *
                        </div>
                        <input 
                            type="tel"
                            // @ts-ignore
                            name="cpf"
                            {...register('cpf')}
                            placeholder="Ex.: 12345678910"
                            />
                        {errors.cpf && <span>{errors.cpf.message}</span> }
                    </label>
    
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
    
                    <button type="submit">Enviar</button>
                    
                </form>
    
            </section>
        </Container>
    )
}
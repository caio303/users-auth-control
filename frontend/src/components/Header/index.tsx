import { Link} from 'react-router-dom';

import { Container } from './styles';

export const Header = () => (
    <Container>
        <div id='logo'>
            <a href='https://github.com/caio303?tab=repositories'>
                <i className="devicon-github-original"></i>
            </a>
        </div>
        <nav>
            <Link to="/perfil">
                Perfil
            </Link>
            <Link to="/login">
                Login
            </Link>
            <Link to="/cadastro">
                Cadastre-se
            </Link>
        </nav>
    </Container>
)
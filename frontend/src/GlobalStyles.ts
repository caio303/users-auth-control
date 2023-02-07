import { createGlobalStyle } from 'styled-components';

export const GlobalStyles = createGlobalStyle`
    :root {
        --dark-blue: #0B132B;
        --dark-blue-light: #1C2541;
        --dark-blue-lighter: #2B3B56;
        --light-cyan: #6FFFE9;
        --form-cyan: #4B8895;
    }
    
    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
        color: #fff;
        max-width: 100vw;
    }

    html {
        width: calc(100vw - 1.1rem);
        min-height: 100vh;
        font-family: 'Raleway', sans-serif;
         
        * {
            max-width: 100vw;
        }
    }

    body {
        width: calc(100vw - 1.1rem);
        min-height: 100vh;
        background-color: var(--dark-blue);
        
    }

    @media only screen and (max-width: 1490px) {
        body, html {
            width: 100vw;
        }
    }

    a {
        text-decoration: none;

        &:visited {
            color: inherit;
        }
    }
`
import styled from 'styled-components';

export const Container = styled.header`
    height: 4.2rem;
    background-color: var(--dark-blue);
    padding: 1rem 2.6rem;
    border-bottom: .1rem solid var(--dark-blue-light);
    font-weight: 600;
    font-size: 1.1rem;
    display: flex;
    align-items: center;
    gap: 1.5rem;
    position: sticky;
    top: 0;
    z-index: 3;

    @media only screen and (max-width: 480px) {
        padding: 1rem .8rem;
    }

    #logo {
        font-size: 2.2rem;
    }

    nav {
        display: flex;
        gap: 1rem;
    }

    .onScroll {
        opacity: .7;
    }
`
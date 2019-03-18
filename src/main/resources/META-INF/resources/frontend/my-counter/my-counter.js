const template = document.createElement('template');
template.innerHTML = `
<style>
    :host div {
        border: 1px solid grey;
        margin: 0 auto;
        text-align: center;
    }
    button, span {
        padding: 5px;
        margin: 5px;
        font-size: 25px;
    }
</style>
<div>
    <span class="value"></span>
    <br />
    <button class="minus"> - </button>
    <button class="plus"> + </button>
</div>
<br />
`;

export class MyCounter extends HTMLElement {
    constructor() {
        super();

        this.rootElement = this.attachShadow({mode: 'open'});
        this.rootElement.appendChild(template.content.cloneNode(true));

        this.valueElement = this.rootElement.querySelector('.value');

        this.plusButton = this.rootElement.querySelector('.plus');
        this.minusButton = this.rootElement.querySelector('.minus');

        this.plusButton
            .addEventListener('click', e => this.increaseCounter(e));

        this.minusButton
            .addEventListener('click', e => this.decreaseCounter(e));
    }

    connectedCallback() {
        if (!this.hasAttribute("value")) {
            this.value = 0;
        }
    }

    static get observedAttributes() {
        return ['value'];
    }

    increaseCounter() {
        this.value++;
        this.dispatchEvent(new CustomEvent("action", {
            detail: "increase"
        }));
    }

    decreaseCounter() {
        this.value--;
        this.dispatchEvent(new CustomEvent("action", {
            detail: "decrease"
        }));
    }

    get value() {
        console.log("get value: " + this._value);
        return this._value;
    }

    set value(v) {
        let n = parseInt(v, 10);
        if (n === this._value) {
            return;
        }
        console.log("set value: " + v);
        this._value = n;
        this.valueElement.innerText = n;
        this.setAttribute("value", n);
        this.dispatchEvent(new CustomEvent("change", {detail: n}));
    }

    attributeChangedCallback(name, oldValue, newValue) {
        if (name === "value" && oldValue !== newValue) {
            this.value = newValue;
        }
    }
}

window.customElements.define('my-counter', MyCounter);

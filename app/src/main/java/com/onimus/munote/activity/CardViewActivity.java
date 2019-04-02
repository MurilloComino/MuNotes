/*
 *
 *  * Created by Murillo Comino on 09/02/19 12:26
 *  * Github: github.com/MurilloComino
 *  * StackOverFlow: pt.stackoverflow.com/users/128573
 *  * Email: murillo_comino@hotmail.com
 *  *
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 09/02/19 12:11
 *
 */

package com.onimus.munote.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.TextView;

import com.onimus.munote.R;
import com.onimus.munote.bancos.dao.CardDao;
import com.onimus.munote.bancos.model.Card;
import com.onimus.munote.files.MenuToolbar;

import static com.onimus.munote.Constants.*;

public class CardViewActivity extends MenuToolbar {

    private Context context;
    private CardDao cardDao;

    private TextView tv_desc_card;

    private Toolbar toolbar;

    private CheckBox cb_credito;
    private CheckBox cb_debito;

    private long idAtual;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_view_screen);

        startVariables();
        startAction();
    }

    private void startVariables() {
        context = getBaseContext();
        //
        cardDao = new CardDao(context);
        //
        getParameters();

        toolbar = findViewById(R.id.toolbar);
        tv_desc_card = findViewById(R.id.tv_desc_card);
        cb_credito = findViewById(R.id.cb_credito);
        cb_debito = findViewById(R.id.cb_debito);
        //
        loadAdmob();
        //
    }

    private void startAction() {
        setSupportActionBar(toolbar);

        setField();

        setAlertDialogDeleteOnClickActivity(R.id.btn_deletar, CardActivity.class, context, idAtual, CARD);
        setActionOnClickActivity(R.id.btn_editar, CardEditActivity.class, idAtual);
    }

    private void setField() {
        if (idAtual != -1) {
            Card cAux = cardDao.getCardById(idAtual);

            tv_desc_card.setText(cAux.getDescCard());

            cb_credito.setEnabled(false);
            cb_debito.setEnabled(false);
            if (cAux.getType() == 1) {
                cb_credito.setChecked(true);
                cb_debito.setChecked(false);
            }
            if (cAux.getType() == 2) {
                cb_credito.setChecked(false);
                cb_debito.setChecked(true);
            }
            if (cAux.getType() == 3) {
                cb_credito.setChecked(true);
                cb_debito.setChecked(true);
            }
        }
    }

    private void getParameters() {
        idAtual = getIntent().getLongExtra(DBASE_ID, 0);

    }

    public void onBackPressed() {
        callActivity(context, CardActivity.class);
    }
}


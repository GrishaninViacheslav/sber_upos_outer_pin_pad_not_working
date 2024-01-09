package ru.mars_groupe.sber_upos_example

import android.content.Intent
import android.os.RemoteException
import android.util.Log
import ru.evotor.framework.core.IntegrationService
import ru.evotor.framework.core.action.event.receipt.payment.combined.PaymentDelegatorEventProcessor
import ru.evotor.framework.core.action.event.receipt.payment.combined.event.PaymentDelegatorEvent
import ru.evotor.framework.core.action.event.receipt.payment.system.PaymentSystemProcessor
import ru.evotor.framework.core.action.event.receipt.payment.system.event.PaymentSystemEvent
import ru.evotor.framework.core.action.event.receipt.payment.system.event.PaymentSystemPaybackCancelEvent
import ru.evotor.framework.core.action.event.receipt.payment.system.event.PaymentSystemPaybackEvent
import ru.evotor.framework.core.action.event.receipt.payment.system.event.PaymentSystemSellCancelEvent
import ru.evotor.framework.core.action.event.receipt.payment.system.event.PaymentSystemSellEvent
import ru.evotor.framework.core.action.processor.ActionProcessor

class PaymentService : IntegrationService() {

    companion object {
        const val TAG = "PaymentService"
        const val EXTRA_NAME_OPERATION = "EXTRA_NAME_OPERATION"
        const val RECEIPT_UUID = "RECEIPT_UUID"
    }

    override fun createProcessors(): Map<String, ActionProcessor> {
        val processorMap: MutableMap<String, ActionProcessor> = HashMap()

        processorMap[PaymentSystemEvent.NAME_ACTION] = object : PaymentSystemProcessor() {
            override fun sell(action: String, event: PaymentSystemSellEvent,
                              callback: Callback
            ) {
                Log.d(TAG, "Sell event: $event")
                val intent = Intent(this@PaymentService, MainActivity::class.java)
                intent.putExtra(RECEIPT_UUID, event.receiptUuid)
                intent.putExtra(EXTRA_NAME_OPERATION, "sell")
                try {
                    callback.startActivity(intent)
                } catch (exc: RemoteException) {
                    Log.e(TAG, "Error while starting payment activity", exc)
                }
            }

            override fun sellCancel(action: String, event: PaymentSystemSellCancelEvent,
                                    callback: Callback
            ) { }

            override fun payback(action: String, event: PaymentSystemPaybackEvent,
                                 callback: Callback
            ) { }

            override fun paybackCancel(action: String, event: PaymentSystemPaybackCancelEvent,
                                       callback: Callback
            ) {}
        }

        processorMap[PaymentDelegatorEvent.NAME_ACTION] = object : PaymentDelegatorEventProcessor() {
            override fun call(action: String, event: PaymentDelegatorEvent, callback: Callback) {
                Log.d(TAG, "Combo payment event: $event")
                val intent = Intent(this@PaymentService, MainActivity::class.java)
                intent.putExtra(RECEIPT_UUID, event.receiptUuid)
                callback.startActivity(intent)
            }
        }

        return processorMap
    }
}

export const ClaimsHomeTableColumns =[{
    name: "Date",
    props: "creationDate",
    type: "Date",
    show: true
}, {
    name: "Status",
    props: "claimStatus",
    type: "text",
    show: true
}, {
    name: "Master Acct",
    props: "masterAcct",
    type: "text",
    show: true
}, {
    name: "Document Type",
    props: "documentType",
    type: "text",
    show: true
},
{
    name: "Facility",
    props: "facilityId",
    type: "text",
    show: true
}, {
    name: "Account",
    props: "account",
    type: "text",
    show: true
}, {
    name: "Amc Claim",
    props: "serviceProviderClaimId",
    type: "text",
    show: true
}, {
    name: "Claim Type",
    props: "claimType",
    type: "text",
    show: true
}, {
    name: "Category",
    props: "category",
    type: "text",
    show: true
}, {
    name: "Pallet Quantity",
    props: "palletQuantity",
    type: "number",
    show: false
}, {
    name: "Claimed Amount",
    props: "claimedAmount",
    type: "text",
    show: false
},
{
    name: "Paid Amount",
    props: "paidAmount",
    type: "number",
    show: false
}, {
    name: "Date Closed",
    props: "dateClosed",
    type: "Date",
    show: false
}, {
    name: "Carrier",
    props: "carrier",
    type: "text",
    show: false
}, {
    name: "Load Number",
    props: "loadNumber",
    type: "number",
    show: false
}];